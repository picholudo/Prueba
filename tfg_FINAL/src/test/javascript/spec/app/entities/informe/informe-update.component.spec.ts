import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { InformeUpdateComponent } from 'app/entities/informe/informe-update.component';
import { InformeService } from 'app/entities/informe/informe.service';
import { Informe } from 'app/shared/model/informe.model';

describe('Component Tests', () => {
  describe('Informe Management Update Component', () => {
    let comp: InformeUpdateComponent;
    let fixture: ComponentFixture<InformeUpdateComponent>;
    let service: InformeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [InformeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(InformeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InformeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InformeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Informe(123);
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
        const entity = new Informe();
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
