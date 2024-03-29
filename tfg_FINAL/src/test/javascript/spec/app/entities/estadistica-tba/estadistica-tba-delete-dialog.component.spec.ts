import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { EstadisticaTBADeleteDialogComponent } from 'app/entities/estadistica-tba/estadistica-tba-delete-dialog.component';
import { EstadisticaTBAService } from 'app/entities/estadistica-tba/estadistica-tba.service';

describe('Component Tests', () => {
  describe('EstadisticaTBA Management Delete Component', () => {
    let comp: EstadisticaTBADeleteDialogComponent;
    let fixture: ComponentFixture<EstadisticaTBADeleteDialogComponent>;
    let service: EstadisticaTBAService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaTBADeleteDialogComponent]
      })
        .overrideTemplate(EstadisticaTBADeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaTBADeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaTBAService);
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
