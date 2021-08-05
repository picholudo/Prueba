import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticaPuntoCorte, EstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';
import { EstadisticaPuntoCorteService } from './estadistica-punto-corte.service';
import { EstadisticaPuntoCorteComponent } from './estadistica-punto-corte.component';
import { EstadisticaPuntoCorteDetailComponent } from './estadistica-punto-corte-detail.component';
import { EstadisticaPuntoCorteUpdateComponent } from './estadistica-punto-corte-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticaPuntoCorteResolve implements Resolve<IEstadisticaPuntoCorte> {
  constructor(private service: EstadisticaPuntoCorteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticaPuntoCorte> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticaPuntoCorte: HttpResponse<EstadisticaPuntoCorte>) => {
          if (estadisticaPuntoCorte.body) {
            return of(estadisticaPuntoCorte.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadisticaPuntoCorte());
  }
}

export const estadisticaPuntoCorteRoute: Routes = [
  {
    path: '',
    component: EstadisticaPuntoCorteComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaPuntoCorte.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticaPuntoCorteDetailComponent,
    resolve: {
      estadisticaPuntoCorte: EstadisticaPuntoCorteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaPuntoCorte.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticaPuntoCorteUpdateComponent,
    resolve: {
      estadisticaPuntoCorte: EstadisticaPuntoCorteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaPuntoCorte.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticaPuntoCorteUpdateComponent,
    resolve: {
      estadisticaPuntoCorte: EstadisticaPuntoCorteResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaPuntoCorte.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
