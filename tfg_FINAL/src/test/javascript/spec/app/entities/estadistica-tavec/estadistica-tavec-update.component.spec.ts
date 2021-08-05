import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaTAVECUpdateComponent } from 'app/entities/estadistica-tavec/estadistica-tavec-update.component';
import { EstadisticaTAVECService } from 'app/entities/estadistica-tavec/estadistica-tavec.service';
import { EstadisticaTAVEC } from 'app/shared/model/estadistica-tavec.model';

describe('Component Tests', () => {
  describe('EstadisticaTAVEC Management Update Component', () => {
    let comp: EstadisticaTAVECUpdateComponent;
    let fixture: ComponentFixture<EstadisticaTAVECUpdateComponent>;
    let service: EstadisticaTAVECService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaTAVECUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EstadisticaTAVECUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EstadisticaTAVECUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EstadisticaTAVECService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EstadisticaTAVEC(123);
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
        const entity = new EstadisticaTAVEC();
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
