import {Component, Inject, LOCALE_ID} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'hotel';

  constructor(@Inject(LOCALE_ID) protected localeId: string) {
    console.log(document.location.origin);
    if (document.location.origin.includes("localhost")) {
      return;
    }
    const languageFromLocalStorage = localStorage.getItem('language');
    if (!languageFromLocalStorage) {
      localStorage.setItem('language', localeId);
    } else if (languageFromLocalStorage !== localeId) {
      const pathItems = document.location.pathname.split('/');
      console.log(document.location.pathname);
      console.log(pathItems);
      if (pathItems.length > 1 && (pathItems[1] === 'hu' || pathItems[1] === 'en' || pathItems[1] === '')) {
        pathItems[1] = languageFromLocalStorage;
        const newUrl = document.location.origin + pathItems.join('/') + '/';
        document.location.replace(newUrl);
      }
    }
  }
}
