import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPuntuacionPrueba, PuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';
import { PuntuacionPruebaService } from './puntuacion-prueba.service';
import { PuntuacionPruebaComponent } from './puntuacion-prueba.component';
import { PuntuacionPruebaDetailComponent } from './puntuacion-prueba-detail.component';
import { PuntuacionPruebaUpdateComponent } from './puntuacion-prueba-update.component';

@Injectable({ providedIn: 'root' })
export class PuntuacionPruebaResolve implements Resolve<IPuntuacionPrueba> {
  constructor(private service: PuntuacionPruebaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPuntuacionPrueba> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((puntuacionPrueba: HttpResponse<PuntuacionPrueba>) => {
          if (puntuacionPrueba.body) {
            return of(puntuacionPrueba.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PuntuacionPrueba());
  }
}

export const puntuacionPruebaRoute: Routes = [
  {
    path: '',
    component: PuntuacionPruebaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.puntuacionPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PuntuacionPruebaDetailComponent,
    resolve: {
      puntuacionPrueba: PuntuacionPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.puntuacionPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PuntuacionPruebaUpdateComponent,
    resolve: {
      puntuacionPrueba: PuntuacionPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.puntuacionPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PuntuacionPruebaUpdateComponent,
    resolve: {
      puntuacionPrueba: PuntuacionPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.puntuacionPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
