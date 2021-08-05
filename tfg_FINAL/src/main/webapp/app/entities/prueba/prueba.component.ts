import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPrueba } from 'app/shared/model/prueba.model';
import { PruebaService } from './prueba.service';
import { PruebaDeleteDialogComponent } from './prueba-delete-dialog.component';

@Component({
  selector: 'jhi-prueba',
  templateUrl: './prueba.component.html'
})
export class PruebaComponent implements OnInit, OnDestroy {
  pruebas?: IPrueba[];
  eventSubscriber?: Subscription;

  constructor(protected pruebaService: PruebaService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.pruebaService.query().subscribe((res: HttpResponse<IPrueba[]>) => {
      this.pruebas = res.body ? res.body : [];
    });
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPruebas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPrueba): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPruebas(): void {
    this.eventSubscriber = this.eventManager.subscribe('pruebaListModification', () => this.loadAll());
  }

  delete(prueba: IPrueba): void {
    const modalRef = this.modalService.open(PruebaDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.prueba = prueba;
  }
}
