import { Component, ElementRef, afterNextRender, inject, output, signal, viewChild } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { firstValueFrom } from 'rxjs';
import { form, FormField, pattern, required, submit } from '@angular/forms/signals';

const LOGIN_HASH = '#bejelentkezes';

export interface LoggedInUser {
  id: number;
  username: string;
  email: string;
}

@Component({
  selector: 'app-login-dialog',
  imports: [FormField],
  templateUrl: './login-dialog.html',
  styleUrl: './login-dialog.css',
  host: { '(window:hashchange)': 'openIfHash()' },
})
export class LoginDialog {
  private readonly http = inject(HttpClient);
  private readonly dialog = viewChild.required<ElementRef<HTMLDialogElement>>('dialog');

  readonly loggedIn = output<LoggedInUser>();

  protected readonly model = signal({ name: '', password: '', remember: false });

  protected readonly loginForm = form(this.model, (p) => {
    required(p.name, { message: 'Add meg a Minecraft neved.' });
    pattern(p.name, /^[A-Za-z0-9_]{3,16}$/, {
      message: 'A Minecraft név 3–16 karakter: betű, szám vagy _.',
    });
    required(p.password, { message: 'Add meg a jelszavad.' });
  });

  protected readonly showPassword = signal(false);
  protected readonly error = signal<string | null>(null);

  constructor() {
    // ha valaki eleve a /#bejelentkezes címet nyitja meg
    afterNextRender(() => this.openIfHash());
  }

  protected openIfHash(): void {
    const dialog = this.dialog().nativeElement;
    if (location.hash === LOGIN_HASH && !dialog.open) {
      dialog.showModal();
    }
  }

  protected close(): void {
    this.dialog().nativeElement.close();
  }

  // X gomb, Esc és sikeres belépés után is lefut
  protected onClosed(): void {
    if (location.hash === LOGIN_HASH) {
      history.replaceState(null, '', location.pathname + location.search);
    }
    this.error.set(null);
    this.showPassword.set(false);
  }

  // kattintás a sötét háttérre bezárja az ablakot
  protected onDialogClick(event: MouseEvent): void {
    if (event.target === this.dialog().nativeElement) {
      this.close();
    }
  }

  protected togglePassword(): void {
    this.showPassword.update((v) => !v);
  }

  protected async onSubmit(event: Event): Promise<void> {
    event.preventDefault();
    this.error.set(null);
    await submit(this.loginForm, async () => {
      try {
        const { name, password } = this.model();
        const user = await firstValueFrom(
          this.http.post<LoggedInUser>('/api/auth/login', { identifier: name, password }),
        );
        this.model.update((m) => ({ ...m, password: '' }));
        this.loggedIn.emit(user);
        this.close();
      } catch (e) {
        this.error.set(
          e instanceof HttpErrorResponse && e.status === 401
            ? 'Hibás Minecraft név vagy jelszó.'
            : 'Nem sikerült bejelentkezni, próbáld újra később.',
        );
      }
      return undefined;
    });
  }
}
