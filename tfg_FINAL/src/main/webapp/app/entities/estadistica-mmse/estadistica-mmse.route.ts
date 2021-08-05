import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticaMMSE, EstadisticaMMSE } from 'app/shared/model/estadistica-mmse.model';
import { EstadisticaMMSEService } from './estadistica-mmse.service';
import { EstadisticaMMSEComponent } from './estadistica-mmse.component';
import { EstadisticaMMSEDetailComponent } from './estadistica-mmse-detail.component';
import { EstadisticaMMSEUpdateComponent } from './estadistica-mmse-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticaMMSEResolve implements Resolve<IEstadisticaMMSE> {
  constructor(private service: EstadisticaMMSEService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticaMMSE> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticaMMSE: HttpResponse<EstadisticaMMSE>) => {
          if (estadisticaMMSE.body) {
            return of(estadisticaMMSE.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadisticaMMSE());
  }
}

export const estadisticaMMSERoute: Routes = [
  {
    path: '',
    component: EstadisticaMMSEComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaMMSE.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticaMMSEDetailComponent,
    resolve: {
      estadisticaMMSE: EstadisticaMMSEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaMMSE.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticaMMSEUpdateComponent,
    resolve: {
      estadisticaMMSE: EstadisticaMMSEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaMMSE.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticaMMSEUpdateComponent,
    resolve: {
      estadisticaMMSE: EstadisticaMMSEResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaMMSE.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
