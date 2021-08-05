import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ResultadoPruebaUpdateComponent } from 'app/entities/resultado-prueba/resultado-prueba-update.component';
import { ResultadoPruebaService } from 'app/entities/resultado-prueba/resultado-prueba.service';
import { ResultadoPrueba } from 'app/shared/model/resultado-prueba.model';

describe('Component Tests', () => {
  describe('ResultadoPrueba Management Update Component', () => {
    let comp: ResultadoPruebaUpdateComponent;
    let fixture: ComponentFixture<ResultadoPruebaUpdateComponent>;
    let service: ResultadoPruebaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ResultadoPruebaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ResultadoPruebaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ResultadoPruebaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ResultadoPruebaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ResultadoPrueba(123);
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
        const entity = new ResultadoPrueba();
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
