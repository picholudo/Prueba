import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticaFAB, EstadisticaFAB } from 'app/shared/model/estadistica-fab.model';
import { EstadisticaFABService } from './estadistica-fab.service';
import { EstadisticaFABComponent } from './estadistica-fab.component';
import { EstadisticaFABDetailComponent } from './estadistica-fab-detail.component';
import { EstadisticaFABUpdateComponent } from './estadistica-fab-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticaFABResolve implements Resolve<IEstadisticaFAB> {
  constructor(private service: EstadisticaFABService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticaFAB> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticaFAB: HttpResponse<EstadisticaFAB>) => {
          if (estadisticaFAB.body) {
            return of(estadisticaFAB.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadisticaFAB());
  }
}

export const estadisticaFABRoute: Routes = [
  {
    path: '',
    component: EstadisticaFABComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaFAB.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticaFABDetailComponent,
    resolve: {
      estadisticaFAB: EstadisticaFABResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaFAB.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticaFABUpdateComponent,
    resolve: {
      estadisticaFAB: EstadisticaFABResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaFAB.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticaFABUpdateComponent,
    resolve: {
      estadisticaFAB: EstadisticaFABResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaFAB.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
