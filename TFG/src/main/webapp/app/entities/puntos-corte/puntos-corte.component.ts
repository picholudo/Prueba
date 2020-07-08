import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPuntosCorte } from 'app/shared/model/puntos-corte.model';
import { PuntosCorteService } from './puntos-corte.service';
import { PuntosCorteDeleteDialogComponent } from './puntos-corte-delete-dialog.component';


@Component({
  selector: 'jhi-puntos-corte',
  templateUrl: './puntos-corte.component.html'
})
export class PuntosCorteComponent implements OnInit, OnDestroy {
  puntosCortes?: IPuntosCorte[];
  eventSubscriber?: Subscription;

  constructor(
    protected puntosCorteService: PuntosCorteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.puntosCorteService.query().subscribe((res: HttpResponse<IPuntosCorte[]>) => {
      this.puntosCortes = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPuntosCortes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPuntosCorte): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPuntosCortes(): void {
    this.eventSubscriber = this.eventManager.subscribe('puntosCorteListModification', () => this.loadAll());
  }

  delete(puntosCorte: IPuntosCorte): void {
    const modalRef = this.modalService.open(PuntosCorteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.puntosCorte = puntosCorte;
  }
  deleteAll(): void {
    this.puntosCorteService.deleteAll().subscribe(() => {
      this.eventManager.broadcast('puntosCorteListModification');
      console.log("PuntosCorteComponent");
    });
    // this.puntosCorteService.deleteAll();

    // const modalRef = this.modalService.open(PuntosCorteDeleteAllDialogComponent, { size: 'lg', backdrop: 'static' });
    /* modalRef.componentInstance.puntosCorte = puntosCorte; */
  }

}
