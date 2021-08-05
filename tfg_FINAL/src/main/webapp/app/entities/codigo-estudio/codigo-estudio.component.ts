import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICodigoEstudio } from 'app/shared/model/codigo-estudio.model';
import { CodigoEstudioService } from './codigo-estudio.service';
import { CodigoEstudioDeleteDialogComponent } from './codigo-estudio-delete-dialog.component';

@Component({
  selector: 'jhi-codigo-estudio',
  templateUrl: './codigo-estudio.component.html'
})
export class CodigoEstudioComponent implements OnInit, OnDestroy {
  codigoEstudios?: ICodigoEstudio[];
  eventSubscriber?: Subscription;

  constructor(
    protected codigoEstudioService: CodigoEstudioService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.codigoEstudioService.query().subscribe((res: HttpResponse<ICodigoEstudio[]>) => {
      this.codigoEstudios = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCodigoEstudios();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICodigoEstudio): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCodigoEstudios(): void {
    this.eventSubscriber = this.eventManager.subscribe('codigoEstudioListModification', () => this.loadAll());
  }

  delete(codigoEstudio: ICodigoEstudio): void {
    const modalRef = this.modalService.open(CodigoEstudioDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.codigoEstudio = codigoEstudio;
  }
}
