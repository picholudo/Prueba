import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInforme, Informe } from 'app/shared/model/informe.model';
import { InformeService } from './informe.service';
import { InformeComponent } from './informe.component';
import { InformeDetailComponent } from './informe-detail.component';
import { InformeUpdateComponent } from './informe-update.component';

@Injectable({ providedIn: 'root' })
export class InformeResolve implements Resolve<IInforme> {
  constructor(private service: InformeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInforme> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((informe: HttpResponse<Informe>) => {
          if (informe.body) {
            return of(informe.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Informe());
  }
}

export const informeRoute: Routes = [
  {
    path: '',
    component: InformeComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.informe.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: InformeDetailComponent,
    resolve: {
      informe: InformeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.informe.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: InformeUpdateComponent,
    resolve: {
      informe: InformeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.informe.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: InformeUpdateComponent,
    resolve: {
      informe: InformeResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.informe.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
