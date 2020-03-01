import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPuntosCorte, PuntosCorte } from 'app/shared/model/puntos-corte.model';
import { PuntosCorteService } from './puntos-corte.service';
import { PuntosCorteComponent } from './puntos-corte.component';
import { PuntosCorteDetailComponent } from './puntos-corte-detail.component';
import { PuntosCorteUpdateComponent } from './puntos-corte-update.component';

@Injectable({ providedIn: 'root' })
export class PuntosCorteResolve implements Resolve<IPuntosCorte> {
  constructor(private service: PuntosCorteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPuntosCorte> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((puntosCorte: HttpResponse<PuntosCorte>) => {
          if (puntosCorte.body) {
            return of(puntosCorte.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PuntosCorte());
  }
}

export const puntosCorteRoute: Routes = [
  {
    path: '',
    component: PuntosCorteComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.puntosCorte.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PuntosCorteDetailComponent,
    resolve: {
      puntosCorte: PuntosCorteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.puntosCorte.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PuntosCorteUpdateComponent,
    resolve: {
      puntosCorte: PuntosCorteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.puntosCorte.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PuntosCorteUpdateComponent,
    resolve: {
      puntosCorte: PuntosCorteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.puntosCorte.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
