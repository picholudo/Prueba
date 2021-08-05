import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticaTBA, EstadisticaTBA } from 'app/shared/model/estadistica-tba.model';
import { EstadisticaTBAService } from './estadistica-tba.service';
import { EstadisticaTBAComponent } from './estadistica-tba.component';
import { EstadisticaTBADetailComponent } from './estadistica-tba-detail.component';
import { EstadisticaTBAUpdateComponent } from './estadistica-tba-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticaTBAResolve implements Resolve<IEstadisticaTBA> {
  constructor(private service: EstadisticaTBAService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticaTBA> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticaTBA: HttpResponse<EstadisticaTBA>) => {
          if (estadisticaTBA.body) {
            return of(estadisticaTBA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadisticaTBA());
  }
}

export const estadisticaTBARoute: Routes = [
  {
    path: '',
    component: EstadisticaTBAComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaTBA.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticaTBADetailComponent,
    resolve: {
      estadisticaTBA: EstadisticaTBAResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaTBA.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticaTBAUpdateComponent,
    resolve: {
      estadisticaTBA: EstadisticaTBAResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaTBA.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticaTBAUpdateComponent,
    resolve: {
      estadisticaTBA: EstadisticaTBAResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaTBA.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
