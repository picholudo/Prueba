import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaMMSEDetailComponent } from 'app/entities/estadistica-mmse/estadistica-mmse-detail.component';
import { EstadisticaMMSE } from 'app/shared/model/estadistica-mmse.model';

describe('Component Tests', () => {
  describe('EstadisticaMMSE Management Detail Component', () => {
    let comp: EstadisticaMMSEDetailComponent;
    let fixture: ComponentFixture<EstadisticaMMSEDetailComponent>;
    const route = ({ data: of({ estadisticaMMSE: new EstadisticaMMSE(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaMMSEDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticaMMSEDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaMMSEDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticaMMSE on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticaMMSE).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
