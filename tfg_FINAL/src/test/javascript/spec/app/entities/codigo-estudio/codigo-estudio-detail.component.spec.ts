import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { CodigoEstudioDetailComponent } from 'app/entities/codigo-estudio/codigo-estudio-detail.component';
import { CodigoEstudio } from 'app/shared/model/codigo-estudio.model';

describe('Component Tests', () => {
  describe('CodigoEstudio Management Detail Component', () => {
    let comp: CodigoEstudioDetailComponent;
    let fixture: ComponentFixture<CodigoEstudioDetailComponent>;
    const route = ({ data: of({ codigoEstudio: new CodigoEstudio(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [CodigoEstudioDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CodigoEstudioDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CodigoEstudioDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load codigoEstudio on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.codigoEstudio).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
