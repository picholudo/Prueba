import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrueba, Prueba } from 'app/shared/model/prueba.model';
import { PruebaService } from './prueba.service';
import { PruebaComponent } from './prueba.component';
import { PruebaDetailComponent } from './prueba-detail.component';
import { PruebaUpdateComponent } from './prueba-update.component';

@Injectable({ providedIn: 'root' })
export class PruebaResolve implements Resolve<IPrueba> {
  constructor(private service: PruebaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrueba> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((prueba: HttpResponse<Prueba>) => {
          if (prueba.body) {
            return of(prueba.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Prueba());
  }
}

export const pruebaRoute: Routes = [
  {
    path: '',
    component: PruebaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.prueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PruebaDetailComponent,
    resolve: {
      prueba: PruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.prueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PruebaUpdateComponent,
    resolve: {
      prueba: PruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.prueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PruebaUpdateComponent,
    resolve: {
      prueba: PruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.prueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
