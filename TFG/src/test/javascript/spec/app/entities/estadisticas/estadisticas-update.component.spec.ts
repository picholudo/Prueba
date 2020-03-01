import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticasUpdateComponent } from 'app/entities/estadisticas/estadisticas-update.component';
import { EstadisticasService } from 'app/entities/estadisticas/estadisticas.service';
import { Estadisticas } from 'app/shared/model/estadisticas.model';

describe('Component Tests', () => {
  describe('Estadisticas Management Update Component', () => {
    let comp: EstadisticasUpdateComponent;
    let fixture: ComponentFixture<EstadisticasUpdateComponent>;
    let service: EstadisticasService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticasUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EstadisticasUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticasUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticasService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Estadisticas(123);
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
        const entity = new Estadisticas();
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
