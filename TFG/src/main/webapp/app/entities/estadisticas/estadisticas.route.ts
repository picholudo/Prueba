import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticas, Estadisticas } from 'app/shared/model/estadisticas.model';
import { EstadisticasService } from './estadisticas.service';
import { EstadisticasComponent } from './estadisticas.component';
import { EstadisticasDetailComponent } from './estadisticas-detail.component';
import { EstadisticasUpdateComponent } from './estadisticas-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticasResolve implements Resolve<IEstadisticas> {
  constructor(private service: EstadisticasService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticas> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticas: HttpResponse<Estadisticas>) => {
          if (estadisticas.body) {
            return of(estadisticas.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Estadisticas());
  }
}

export const estadisticasRoute: Routes = [
  {
    path: '',
    component: EstadisticasComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticasDetailComponent,
    resolve: {
      estadisticas: EstadisticasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticasUpdateComponent,
    resolve: {
      estadisticas: EstadisticasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticas.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticasUpdateComponent,
    resolve: {
      estadisticas: EstadisticasResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticas.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
