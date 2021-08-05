import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaPzNeuronormaUpdateComponent } from 'app/entities/estadistica-pz-neuronorma/estadistica-pz-neuronorma-update.component';
import { EstadisticaPzNeuronormaService } from 'app/entities/estadistica-pz-neuronorma/estadistica-pz-neuronorma.service';
import { EstadisticaPzNeuronorma } from 'app/shared/model/estadistica-pz-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaPzNeuronorma Management Update Component', () => {
    let comp: EstadisticaPzNeuronormaUpdateComponent;
    let fixture: ComponentFixture<EstadisticaPzNeuronormaUpdateComponent>;
    let service: EstadisticaPzNeuronormaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaPzNeuronormaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EstadisticaPzNeuronormaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaPzNeuronormaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaPzNeuronormaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadisticaPzNeuronorma(123);
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
        const entity = new EstadisticaPzNeuronorma();
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
