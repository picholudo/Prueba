import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EvaluacionUpdateComponent } from 'app/entities/evaluacion/evaluacion-update.component';
import { EvaluacionService } from 'app/entities/evaluacion/evaluacion.service';
import { Evaluacion } from 'app/shared/model/evaluacion.model';

describe('Component Tests', () => {
  describe('Evaluacion Management Update Component', () => {
    let comp: EvaluacionUpdateComponent;
    let fixture: ComponentFixture<EvaluacionUpdateComponent>;
    let service: EvaluacionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EvaluacionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EvaluacionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EvaluacionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EvaluacionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Evaluacion(123);
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
        const entity = new Evaluacion();
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
