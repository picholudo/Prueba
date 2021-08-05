import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaTBAUpdateComponent } from 'app/entities/estadistica-tba/estadistica-tba-update.component';
import { EstadisticaTBAService } from 'app/entities/estadistica-tba/estadistica-tba.service';
import { EstadisticaTBA } from 'app/shared/model/estadistica-tba.model';

describe('Component Tests', () => {
  describe('EstadisticaTBA Management Update Component', () => {
    let comp: EstadisticaTBAUpdateComponent;
    let fixture: ComponentFixture<EstadisticaTBAUpdateComponent>;
    let service: EstadisticaTBAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaTBAUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EstadisticaTBAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaTBAUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaTBAService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadisticaTBA(123);
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
        const entity = new EstadisticaTBA();
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
