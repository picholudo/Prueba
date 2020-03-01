import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { ZScoreUpdateComponent } from 'app/entities/z-score/z-score-update.component';
import { ZScoreService } from 'app/entities/z-score/z-score.service';
import { ZScore } from 'app/shared/model/z-score.model';

describe('Component Tests', () => {
  describe('ZScore Management Update Component', () => {
    let comp: ZScoreUpdateComponent;
    let fixture: ComponentFixture<ZScoreUpdateComponent>;
    let service: ZScoreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [ZScoreUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ZScoreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ZScoreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ZScoreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ZScore(123);
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
        const entity = new ZScore();
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
