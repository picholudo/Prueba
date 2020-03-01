import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IZScore } from 'app/shared/model/z-score.model';
import { ZScoreService } from './z-score.service';

@Component({
  templateUrl: './z-score-delete-dialog.component.html'
})
export class ZScoreDeleteDialogComponent {
  zScore?: IZScore;

  constructor(protected zScoreService: ZScoreService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.zScoreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('zScoreListModification');
      this.activeModal.close();
    });
  }
}
