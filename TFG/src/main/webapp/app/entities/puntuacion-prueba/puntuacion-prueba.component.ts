import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';
import { PuntuacionPruebaService } from './puntuacion-prueba.service';
import { PuntuacionPruebaDeleteDialogComponent } from './puntuacion-prueba-delete-dialog.component';

@Component({
  selector: 'jhi-puntuacion-prueba',
  templateUrl: './puntuacion-prueba.component.html'
})
export class PuntuacionPruebaComponent implements OnInit, OnDestroy {
  puntuacionPruebas?: IPuntuacionPrueba[];
  eventSubscriber?: Subscription;

  constructor(
    protected puntuacionPruebaService: PuntuacionPruebaService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.puntuacionPruebaService.query().subscribe((res: HttpResponse<IPuntuacionPrueba[]>) => {
      this.puntuacionPruebas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPuntuacionPruebas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPuntuacionPrueba): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPuntuacionPruebas(): void {
    this.eventSubscriber = this.eventManager.subscribe('puntuacionPruebaListModification', () => this.loadAll());
  }

  delete(puntuacionPrueba: IPuntuacionPrueba): void {
    const modalRef = this.modalService.open(PuntuacionPruebaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.puntuacionPrueba = puntuacionPrueba;
  }
}
