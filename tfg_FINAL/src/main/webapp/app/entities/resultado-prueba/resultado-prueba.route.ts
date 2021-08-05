import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IResultadoPrueba, ResultadoPrueba } from 'app/shared/model/resultado-prueba.model';
import { ResultadoPruebaService } from './resultado-prueba.service';
import { ResultadoPruebaComponent } from './resultado-prueba.component';
import { ResultadoPruebaDetailComponent } from './resultado-prueba-detail.component';
import { ResultadoPruebaUpdateComponent } from './resultado-prueba-update.component';

@Injectable({ providedIn: 'root' })
export class ResultadoPruebaResolve implements Resolve<IResultadoPrueba> {
  constructor(private service: ResultadoPruebaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IResultadoPrueba> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((resultadoPrueba: HttpResponse<ResultadoPrueba>) => {
          if (resultadoPrueba.body) {
            return of(resultadoPrueba.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ResultadoPrueba());
  }
}

export const resultadoPruebaRoute: Routes = [
  {
    path: '',
    component: ResultadoPruebaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.resultadoPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ResultadoPruebaDetailComponent,
    resolve: {
      resultadoPrueba: ResultadoPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.resultadoPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ResultadoPruebaUpdateComponent,
    resolve: {
      resultadoPrueba: ResultadoPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.resultadoPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ResultadoPruebaUpdateComponent,
    resolve: {
      resultadoPrueba: ResultadoPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.resultadoPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
