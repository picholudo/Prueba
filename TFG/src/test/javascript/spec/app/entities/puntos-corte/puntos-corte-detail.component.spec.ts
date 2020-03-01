import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PuntosCorteDetailComponent } from 'app/entities/puntos-corte/puntos-corte-detail.component';
import { PuntosCorte } from 'app/shared/model/puntos-corte.model';

describe('Component Tests', () => {
  describe('PuntosCorte Management Detail Component', () => {
    let comp: PuntosCorteDetailComponent;
    let fixture: ComponentFixture<PuntosCorteDetailComponent>;
    const route = ({ data: of({ puntosCorte: new PuntosCorte(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PuntosCorteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PuntosCorteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuntosCorteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load puntosCorte on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.puntosCorte).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
