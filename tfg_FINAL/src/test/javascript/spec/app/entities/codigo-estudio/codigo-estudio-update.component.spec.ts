import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { CodigoEstudioUpdateComponent } from 'app/entities/codigo-estudio/codigo-estudio-update.component';
import { CodigoEstudioService } from 'app/entities/codigo-estudio/codigo-estudio.service';
import { CodigoEstudio } from 'app/shared/model/codigo-estudio.model';

describe('Component Tests', () => {
  describe('CodigoEstudio Management Update Component', () => {
    let comp: CodigoEstudioUpdateComponent;
    let fixture: ComponentFixture<CodigoEstudioUpdateComponent>;
    let service: CodigoEstudioService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CodigoEstudioUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CodigoEstudioUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CodigoEstudioUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CodigoEstudioService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CodigoEstudio(123);
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
        const entity = new CodigoEstudio();
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
