import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaAjusteNeuronormaUpdateComponent } from 'app/entities/estadistica-ajuste-neuronorma/estadistica-ajuste-neuronorma-update.component';
import { EstadisticaAjusteNeuronormaService } from 'app/entities/estadistica-ajuste-neuronorma/estadistica-ajuste-neuronorma.service';
import { EstadisticaAjusteNeuronorma } from 'app/shared/model/estadistica-ajuste-neuronorma.model';

describe('Component Tests', () => {
  describe('EstadisticaAjusteNeuronorma Management Update Component', () => {
    let comp: EstadisticaAjusteNeuronormaUpdateComponent;
    let fixture: ComponentFixture<EstadisticaAjusteNeuronormaUpdateComponent>;
    let service: EstadisticaAjusteNeuronormaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaAjusteNeuronormaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EstadisticaAjusteNeuronormaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaAjusteNeuronormaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaAjusteNeuronormaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadisticaAjusteNeuronorma(123);
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
        const entity = new EstadisticaAjusteNeuronorma();
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
