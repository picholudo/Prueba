import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { AppTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { EstadisticaPzNeuronormaDeleteDialogComponent } from 'app/entities/estadistica-pz-neuronorma/estadistica-pz-neuronorma-delete-dialog.component';
import { EstadisticaPzNeuronormaService } from 'app/entities/estadistica-pz-neuronorma/estadistica-pz-neuronorma.service';

describe('Component Tests', () => {
  describe('EstadisticaPzNeuronorma Management Delete Component', () => {
    let comp: EstadisticaPzNeuronormaDeleteDialogComponent;
    let fixture: ComponentFixture<EstadisticaPzNeuronormaDeleteDialogComponent>;
    let service: EstadisticaPzNeuronormaService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaPzNeuronormaDeleteDialogComponent]
      })
        .overrideTemplate(EstadisticaPzNeuronormaDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaPzNeuronormaDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaPzNeuronormaService);
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
