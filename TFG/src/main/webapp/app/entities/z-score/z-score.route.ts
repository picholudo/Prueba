import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IZScore, ZScore } from 'app/shared/model/z-score.model';
import { ZScoreService } from './z-score.service';
import { ZScoreComponent } from './z-score.component';
import { ZScoreDetailComponent } from './z-score-detail.component';
import { ZScoreUpdateComponent } from './z-score-update.component';

@Injectable({ providedIn: 'root' })
export class ZScoreResolve implements Resolve<IZScore> {
  constructor(private service: ZScoreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IZScore> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((zScore: HttpResponse<ZScore>) => {
          if (zScore.body) {
            return of(zScore.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ZScore());
  }
}

export const zScoreRoute: Routes = [
  {
    path: '',
    component: ZScoreComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.zScore.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ZScoreDetailComponent,
    resolve: {
      zScore: ZScoreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.zScore.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ZScoreUpdateComponent,
    resolve: {
      zScore: ZScoreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.zScore.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ZScoreUpdateComponent,
    resolve: {
      zScore: ZScoreResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'App.zScore.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
