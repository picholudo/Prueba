import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEstadisticaAjusteNeuronorma, EstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';
import { EstadisticaAjusteNeuronormaService } from './estadistica-ajuste-neuronorma.service';
import { EstadisticaAjusteNeuronormaComponent } from './estadistica-ajuste-neuronorma.component';
import { EstadisticaAjusteNeuronormaDetailComponent } from './estadistica-ajuste-neuronorma-detail.component';
import { EstadisticaAjusteNeuronormaUpdateComponent } from './estadistica-ajuste-neuronorma-update.component';

@Injectable({ providedIn: 'root' })
export class EstadisticaAjusteNeuronormaResolve implements Resolve<IEstadisticaAjusteNeuronorma> {
  constructor(private service: EstadisticaAjusteNeuronormaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEstadisticaAjusteNeuronorma> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((estadisticaAjusteNeuronorma: HttpResponse<EstadisticaAjusteNeuronorma>) => {
          if (estadisticaAjusteNeuronorma.body) {
            return of(estadisticaAjusteNeuronorma.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EstadisticaAjusteNeuronorma());
  }
}

export const estadisticaAjusteNeuronormaRoute: Routes = [
  {
    path: '',
    component: EstadisticaAjusteNeuronormaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaAjusteNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EstadisticaAjusteNeuronormaDetailComponent,
    resolve: {
      estadisticaAjusteNeuronorma: EstadisticaAjusteNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaAjusteNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EstadisticaAjusteNeuronormaUpdateComponent,
    resolve: {
      estadisticaAjusteNeuronorma: EstadisticaAjusteNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaAjusteNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EstadisticaAjusteNeuronormaUpdateComponent,
    resolve: {
      estadisticaAjusteNeuronorma: EstadisticaAjusteNeuronormaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.estadisticaAjusteNeuronorma.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
