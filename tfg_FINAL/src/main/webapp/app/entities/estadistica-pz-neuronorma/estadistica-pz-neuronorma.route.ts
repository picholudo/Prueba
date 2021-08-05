import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticaPzNeuronorma, EstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';
import { EstadisticaPzNeuronormaService } from './estadistica-pz-neuronorma.service';
import { EstadisticaPzNeuronormaComponent } from './estadistica-pz-neuronorma.component';
import { EstadisticaPzNeuronormaDetailComponent } from './estadistica-pz-neuronorma-detail.component';
import { EstadisticaPzNeuronormaUpdateComponent } from './estadistica-pz-neuronorma-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticaPzNeuronormaResolve implements Resolve<IEstadisticaPzNeuronorma> {
  constructor(private service: EstadisticaPzNeuronormaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticaPzNeuronorma> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticaPzNeuronorma: HttpResponse<EstadisticaPzNeuronorma>) => {
          if (estadisticaPzNeuronorma.body) {
            return of(estadisticaPzNeuronorma.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadisticaPzNeuronorma());
  }
}

export const estadisticaPzNeuronormaRoute: Routes = [
  {
    path: '',
    component: EstadisticaPzNeuronormaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaPzNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticaPzNeuronormaDetailComponent,
    resolve: {
      estadisticaPzNeuronorma: EstadisticaPzNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaPzNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticaPzNeuronormaUpdateComponent,
    resolve: {
      estadisticaPzNeuronorma: EstadisticaPzNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaPzNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticaPzNeuronormaUpdateComponent,
    resolve: {
      estadisticaPzNeuronorma: EstadisticaPzNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaPzNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
