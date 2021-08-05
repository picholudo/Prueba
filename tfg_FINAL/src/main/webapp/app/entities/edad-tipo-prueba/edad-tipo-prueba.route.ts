import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEdadTipoPrueba, EdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';
import { EdadTipoPruebaService } from './edad-tipo-prueba.service';
import { EdadTipoPruebaComponent } from './edad-tipo-prueba.component';
import { EdadTipoPruebaDetailComponent } from './edad-tipo-prueba-detail.component';
import { EdadTipoPruebaUpdateComponent } from './edad-tipo-prueba-update.component';

@Injectable({ providedIn: 'root' })
export class EdadTipoPruebaResolve implements Resolve<IEdadTipoPrueba> {
  constructor(private service: EdadTipoPruebaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEdadTipoPrueba> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((edadTipoPrueba: HttpResponse<EdadTipoPrueba>) => {
          if (edadTipoPrueba.body) {
            return of(edadTipoPrueba.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EdadTipoPrueba());
  }
}

export const edadTipoPruebaRoute: Routes = [
  {
    path: '',
    component: EdadTipoPruebaComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.edadTipoPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EdadTipoPruebaDetailComponent,
    resolve: {
      edadTipoPrueba: EdadTipoPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.edadTipoPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EdadTipoPruebaUpdateComponent,
    resolve: {
      edadTipoPrueba: EdadTipoPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.edadTipoPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EdadTipoPruebaUpdateComponent,
    resolve: {
      edadTipoPrueba: EdadTipoPruebaResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.edadTipoPrueba.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
