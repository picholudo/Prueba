import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IZScore } from 'app/shared/model/z-score.model';
import { ZScoreService } from './z-score.service';
import { ZScoreDeleteDialogComponent } from './z-score-delete-dialog.component';

@Component({
  selector: 'jhi-z-score',
  templateUrl: './z-score.component.html'
})
export class ZScoreComponent implements OnInit, OnDestroy {
  zScores?: IZScore[];
  eventSubscriber?: Subscription;

  constructor(protected zScoreService: ZScoreService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.zScoreService.query().subscribe((res: HttpResponse<IZScore[]>) => {
      this.zScores = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInZScores();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IZScore): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInZScores(): void {
    this.eventSubscriber = this.eventManager.subscribe('zScoreListModification', () => this.loadAll());
  }

  delete(zScore: IZScore): void {
    const modalRef = this.modalService.open(ZScoreDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.zScore = zScore;
  }
}
