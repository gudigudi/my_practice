describe('Browser notifications', () => {
    it('are supported by the test browser', () => {
        cy.visit('index.html')
        cy.window().should('have.property', 'Notification').should('be.a', 'function')
    })
})