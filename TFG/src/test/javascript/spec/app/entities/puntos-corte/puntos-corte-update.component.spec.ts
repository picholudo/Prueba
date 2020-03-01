import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PuntosCorteUpdateComponent } from 'app/entities/puntos-corte/puntos-corte-update.component';
import { PuntosCorteService } from 'app/entities/puntos-corte/puntos-corte.service';
import { PuntosCorte } from 'app/shared/model/puntos-corte.model';

describe('Component Tests', () => {
  describe('PuntosCorte Management Update Component', () => {
    let comp: PuntosCorteUpdateComponent;
    let fixture: ComponentFixture<PuntosCorteUpdateComponent>;
    let service: PuntosCorteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PuntosCorteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PuntosCorteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PuntosCorteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PuntosCorteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PuntosCorte(123);
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
        const entity = new PuntosCorte();
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
