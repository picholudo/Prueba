import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaFABUpdateComponent } from 'app/entities/estadistica-fab/estadistica-fab-update.component';
import { EstadisticaFABService } from 'app/entities/estadistica-fab/estadistica-fab.service';
import { EstadisticaFAB } from 'app/shared/model/estadistica-fab.model';

describe('Component Tests', () => {
  describe('EstadisticaFAB Management Update Component', () => {
    let comp: EstadisticaFABUpdateComponent;
    let fixture: ComponentFixture<EstadisticaFABUpdateComponent>;
    let service: EstadisticaFABService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaFABUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EstadisticaFABUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaFABUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaFABService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadisticaFAB(123);
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
        const entity = new EstadisticaFAB();
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
