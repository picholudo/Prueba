import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AppSharedModule } from 'app/shared/shared.module';
import { ZScoreComponent } from './z-score.component';
import { ZScoreDetailComponent } from './z-score-detail.component';
import { ZScoreUpdateComponent } from './z-score-update.component';
import { ZScoreDeleteDialogComponent } from './z-score-delete-dialog.component';
import { zScoreRoute } from './z-score.route';

@NgModule({
  imports: [AppSharedModule, RouterModule.forChild(zScoreRoute)],
  declarations: [ZScoreComponent, ZScoreDetailComponent, ZScoreUpdateComponent, ZScoreDeleteDialogComponent],
  entryComponents: [ZScoreDeleteDialogComponent]
})
export class AppZScoreModule {}
