import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { EstadisticaPuntoCorteDetailComponent } from 'app/entities/estadistica-punto-corte/estadistica-punto-corte-detail.component';
import { EstadisticaPuntoCorte } from 'app/shared/model/estadistica-punto-corte.model';

describe('Component Tests', () => {
  describe('EstadisticaPuntoCorte Management Detail Component', () => {
    let comp: EstadisticaPuntoCorteDetailComponent;
    let fixture: ComponentFixture<EstadisticaPuntoCorteDetailComponent>;
    const route = ({ data: of({ estadisticaPuntoCorte: new EstadisticaPuntoCorte(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [EstadisticaPuntoCorteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EstadisticaPuntoCorteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EstadisticaPuntoCorteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load estadisticaPuntoCorte on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.estadisticaPuntoCorte).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
