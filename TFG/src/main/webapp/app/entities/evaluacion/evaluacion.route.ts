import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEvaluacion, Evaluacion } from 'app/shared/model/evaluacion.model';
import { EvaluacionService } from './evaluacion.service';
import { EvaluacionComponent } from './evaluacion.component';
import { EvaluacionDetailComponent } from './evaluacion-detail.component';
import { EvaluacionUpdateComponent } from './evaluacion-update.component';

@Injectable({ providedIn: 'root' })
export class EvaluacionResolve implements Resolve<IEvaluacion> {
  constructor(private service: EvaluacionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEvaluacion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((evaluacion: HttpResponse<Evaluacion>) => {
          if (evaluacion.body) {
            return of(evaluacion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Evaluacion());
  }
}

export const evaluacionRoute: Routes = [
  {
    path: '',
    component: EvaluacionComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.evaluacion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EvaluacionDetailComponent,
    resolve: {
      evaluacion: EvaluacionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.evaluacion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EvaluacionUpdateComponent,
    resolve: {
      evaluacion: EvaluacionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.evaluacion.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EvaluacionUpdateComponent,
    resolve: {
      evaluacion: EvaluacionResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.evaluacion.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
