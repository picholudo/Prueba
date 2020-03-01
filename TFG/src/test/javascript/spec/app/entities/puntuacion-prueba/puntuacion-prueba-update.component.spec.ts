import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PuntuacionPruebaUpdateComponent } from 'app/entities/puntuacion-prueba/puntuacion-prueba-update.component';
import { PuntuacionPruebaService } from 'app/entities/puntuacion-prueba/puntuacion-prueba.service';
import { PuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';

describe('Component Tests', () => {
  describe('PuntuacionPrueba Management Update Component', () => {
    let comp: PuntuacionPruebaUpdateComponent;
    let fixture: ComponentFixture<PuntuacionPruebaUpdateComponent>;
    let service: PuntuacionPruebaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PuntuacionPruebaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PuntuacionPruebaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuntuacionPruebaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuntuacionPruebaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PuntuacionPrueba(123);
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
        const entity = new PuntuacionPrueba();
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
