import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
    if (isPlatformBrowser(this.platformId)) {
      this.loggedIn.next(this.hasToken());
    }
  }

  private hasToken(): boolean {
    if (isPlatformBrowser(this.platformId)) {
      return !!localStorage.getItem('auth_token');
    }
    return false;
  }

  login(email: string): void {
    if (isPlatformBrowser(this.platformId)) {
      const isAdmin = email.endsWith('@ong.com');
      localStorage.setItem('auth_token', 'mock_token_123');
      localStorage.setItem('user_role', isAdmin ? 'admin' : 'user');
      localStorage.setItem('user_email', email);
      this.loggedIn.next(true);
    }
  }

  logout(): void {
    if (isPlatformBrowser(this.platformId)) {
      localStorage.removeItem('auth_token');
      localStorage.removeItem('user_role');
      localStorage.removeItem('user_email');
      this.loggedIn.next(false);
    }
  }

  isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  getUserEmail(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem('user_email');
    }
    return null;
  }
}
