import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EdadTipoPruebaUpdateComponent } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba-update.component';
import { EdadTipoPruebaService } from 'app/entities/edad-tipo-prueba/edad-tipo-prueba.service';
import { EdadTipoPrueba } from 'app/shared/model/edad-tipo-prueba.model';

describe('Component Tests', () => {
  describe('EdadTipoPrueba Management Update Component', () => {
    let comp: EdadTipoPruebaUpdateComponent;
    let fixture: ComponentFixture<EdadTipoPruebaUpdateComponent>;
    let service: EdadTipoPruebaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EdadTipoPruebaUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EdadTipoPruebaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EdadTipoPruebaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EdadTipoPruebaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EdadTipoPrueba(123);
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
        const entity = new EdadTipoPrueba();
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
