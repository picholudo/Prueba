import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaPuntoCorteUpdateComponent } from 'app/entities/estadistica-punto-corte/estadistica-punto-corte-update.component';
import { EstadisticaPuntoCorteService } from 'app/entities/estadistica-punto-corte/estadistica-punto-corte.service';
import { EstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';

describe('Component Tests', () => {
  describe('EstadisticaPuntoCorte Management Update Component', () => {
    let comp: EstadisticaPuntoCorteUpdateComponent;
    let fixture: ComponentFixture<EstadisticaPuntoCorteUpdateComponent>;
    let service: EstadisticaPuntoCorteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaPuntoCorteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EstadisticaPuntoCorteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaPuntoCorteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaPuntoCorteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadisticaPuntoCorte(123);
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
        const entity = new EstadisticaPuntoCorte();
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
