import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICodigoEstudio, CodigoEstudio } from 'app/shared/model/codigo-estudio.model';
import { CodigoEstudioService } from './codigo-estudio.service';
import { CodigoEstudioComponent } from './codigo-estudio.component';
import { CodigoEstudioDetailComponent } from './codigo-estudio-detail.component';
import { CodigoEstudioUpdateComponent } from './codigo-estudio-update.component';

@Injectable({ providedIn: 'root' })
export class CodigoEstudioResolve implements Resolve<ICodigoEstudio> {
  constructor(private service: CodigoEstudioService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICodigoEstudio> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((codigoEstudio: HttpResponse<CodigoEstudio>) => {
          if (codigoEstudio.body) {
            return of(codigoEstudio.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CodigoEstudio());
  }
}

export const codigoEstudioRoute: Routes = [
  {
    path: '',
    component: CodigoEstudioComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.codigoEstudio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CodigoEstudioDetailComponent,
    resolve: {
      codigoEstudio: CodigoEstudioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.codigoEstudio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CodigoEstudioUpdateComponent,
    resolve: {
      codigoEstudio: CodigoEstudioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.codigoEstudio.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CodigoEstudioUpdateComponent,
    resolve: {
      codigoEstudio: CodigoEstudioResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.codigoEstudio.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
