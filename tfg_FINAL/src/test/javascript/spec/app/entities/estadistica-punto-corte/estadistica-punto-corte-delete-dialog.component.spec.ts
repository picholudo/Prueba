import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { EstadisticaPuntoCorteDeleteDialogComponent } from 'app/entities/estadistica-punto-corte/estadistica-punto-corte-delete-dialog.component';
import { EstadisticaPuntoCorteService } from 'app/entities/estadistica-punto-corte/estadistica-punto-corte.service';

describe('Component Tests', () => {
  describe('EstadisticaPuntoCorte Management Delete Component', () => {
    let comp: EstadisticaPuntoCorteDeleteDialogComponent;
    let fixture: ComponentFixture<EstadisticaPuntoCorteDeleteDialogComponent>;
    let service: EstadisticaPuntoCorteService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaPuntoCorteDeleteDialogComponent]
      })
        .overrideTemplate(EstadisticaPuntoCorteDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaPuntoCorteDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaPuntoCorteService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.clear();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
