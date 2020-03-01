import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PruebaUpdateComponent } from 'app/entities/prueba/prueba-update.component';
import { PruebaService } from 'app/entities/prueba/prueba.service';
import { Prueba } from 'app/shared/model/prueba.model';

describe('Component Tests', () => {
  describe('Prueba Management Update Component', () => {
    let comp: PruebaUpdateComponent;
    let fixture: ComponentFixture<PruebaUpdateComponent>;
    let service: PruebaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PruebaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PruebaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PruebaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PruebaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Prueba(123);
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
        const entity = new Prueba();
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
