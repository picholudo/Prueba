import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticaTAVEC, EstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';
import { EstadisticaTAVECService } from './estadistica-tavec.service';
import { EstadisticaTAVECComponent } from './estadistica-tavec.component';
import { EstadisticaTAVECDetailComponent } from './estadistica-tavec-detail.component';
import { EstadisticaTAVECUpdateComponent } from './estadistica-tavec-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticaTAVECResolve implements Resolve<IEstadisticaTAVEC> {
  constructor(private service: EstadisticaTAVECService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticaTAVEC> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticaTAVEC: HttpResponse<EstadisticaTAVEC>) => {
          if (estadisticaTAVEC.body) {
            return of(estadisticaTAVEC.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadisticaTAVEC());
  }
}

export const estadisticaTAVECRoute: Routes = [
  {
    path: '',
    component: EstadisticaTAVECComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaTAVEC.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticaTAVECDetailComponent,
    resolve: {
      estadisticaTAVEC: EstadisticaTAVECResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaTAVEC.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticaTAVECUpdateComponent,
    resolve: {
      estadisticaTAVEC: EstadisticaTAVECResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaTAVEC.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticaTAVECUpdateComponent,
    resolve: {
      estadisticaTAVEC: EstadisticaTAVECResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaTAVEC.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
