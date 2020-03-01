import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AppTestModule } from '../../../test.module';
import { PuntuacionPruebaDetailComponent } from 'app/entities/puntuacion-prueba/puntuacion-prueba-detail.component';
import { PuntuacionPrueba } from 'app/shared/model/puntuacion-prueba.model';

describe('Component Tests', () => {
  describe('PuntuacionPrueba Management Detail Component', () => {
    let comp: PuntuacionPruebaDetailComponent;
    let fixture: ComponentFixture<PuntuacionPruebaDetailComponent>;
    const route = ({ data: of({ puntuacionPrueba: new PuntuacionPrueba(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AppTestModule],
        declarations: [PuntuacionPruebaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PuntuacionPruebaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PuntuacionPruebaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load puntuacionPrueba on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.puntuacionPrueba).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
