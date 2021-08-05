import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaSSNeuronormaUpdateComponent } from 'app/entities/estadistica-ss-neuronorma/estadistica-ss-neuronorma-update.component';
import { EstadisticaSSNeuronormaService } from 'app/entities/estadistica-ss-neuronorma/estadistica-ss-neuronorma.service';
import { EstadisticaSSNeuronorma } from 'app/shared/model/estadistica-ss-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaSSNeuronorma Management Update Component', () => {
    let comp: EstadisticaSSNeuronormaUpdateComponent;
    let fixture: ComponentFixture<EstadisticaSSNeuronormaUpdateComponent>;
    let service: EstadisticaSSNeuronormaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaSSNeuronormaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EstadisticaSSNeuronormaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaSSNeuronormaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaSSNeuronormaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadisticaSSNeuronorma(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadisticaSSNeuronorma();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
