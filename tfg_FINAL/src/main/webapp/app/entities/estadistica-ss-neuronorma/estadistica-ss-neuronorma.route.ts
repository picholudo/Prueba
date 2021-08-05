import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticaSSNeuronorma, EstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';
import { EstadisticaSSNeuronormaService } from './estadistica-ss-neuronorma.service';
import { EstadisticaSSNeuronormaComponent } from './estadistica-ss-neuronorma.component';
import { EstadisticaSSNeuronormaDetailComponent } from './estadistica-ss-neuronorma-detail.component';
import { EstadisticaSSNeuronormaUpdateComponent } from './estadistica-ss-neuronorma-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticaSSNeuronormaResolve implements Resolve<IEstadisticaSSNeuronorma> {
  constructor(private service: EstadisticaSSNeuronormaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticaSSNeuronorma> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticaSSNeuronorma: HttpResponse<EstadisticaSSNeuronorma>) => {
          if (estadisticaSSNeuronorma.body) {
            return of(estadisticaSSNeuronorma.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadisticaSSNeuronorma());
  }
}

export const estadisticaSSNeuronormaRoute: Routes = [
  {
    path: '',
    component: EstadisticaSSNeuronormaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaSSNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticaSSNeuronormaDetailComponent,
    resolve: {
      estadisticaSSNeuronorma: EstadisticaSSNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaSSNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticaSSNeuronormaUpdateComponent,
    resolve: {
      estadisticaSSNeuronorma: EstadisticaSSNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaSSNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticaSSNeuronormaUpdateComponent,
    resolve: {
      estadisticaSSNeuronorma: EstadisticaSSNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaSSNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
